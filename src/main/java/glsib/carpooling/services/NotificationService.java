package glsib.carpooling.services;

import glsib.carpooling.entities.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification getNotification(Long id);
    List<Notification> getAllNotifications();
    Notification updateNotification(Long id, Notification notification);
    void deleteNotification(Long id);
}

