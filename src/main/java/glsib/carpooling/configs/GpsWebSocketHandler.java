package glsib.carpooling.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import glsib.carpooling.entities.GpsData;
import glsib.carpooling.services.GpsDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class GpsWebSocketHandler implements WebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();
    private final GpsDataService gpsDataService;
    private final ObjectMapper objectMapper;

    public GpsWebSocketHandler(GpsDataService gpsDataService, ObjectMapper objectMapper) {
        this.gpsDataService = gpsDataService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        activeSessions.add(session);
        log.info("New WebSocket connection established. Session ID: {}", session.getId());

        // Optionally send a welcome message
        session.sendMessage(new TextMessage("Connected to GPS WebSocket server."));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            // Parse the incoming message as GPS data
            String payload = message.getPayload().toString();
            log.info("Received message from session {}: {}", session.getId(), payload);

            GpsData gpsData = objectMapper.readValue(payload, GpsData.class);

            // Save the data to the database
            GpsData savedData = gpsDataService.saveGpsData(gpsData);
            log.info("Saved GPS data: {}", savedData);

            // Broadcast the saved data to all connected sessions
            String jsonResponse = objectMapper.writeValueAsString(savedData);
            broadcastToAll(jsonResponse);

        } catch (Exception e) {
            log.error("Error processing message from session {}: {}", session.getId(), e.getMessage());
            session.sendMessage(new TextMessage("Error processing GPS data. Please check your input."));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error on session {}: {}", session.getId(), exception.getMessage());
        closeSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("WebSocket connection closed. Session ID: {}, Reason: {}", session.getId(), closeStatus);
        closeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void broadcastToAll(String message) {
        for (WebSocketSession session : activeSessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                    log.info("Sent message to session {}: {}", session.getId(), message);
                }
            } catch (Exception e) {
                log.error("Error sending message to session {}: {}", session.getId(), e.getMessage());
            }
        }
    }

    private void closeSession(WebSocketSession session) {
        activeSessions.remove(session);
        log.info("Session removed: {}", session.getId());
    }
}
