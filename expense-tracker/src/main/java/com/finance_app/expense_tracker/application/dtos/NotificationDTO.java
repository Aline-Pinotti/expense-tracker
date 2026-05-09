package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Notification;
import com.finance_app.expense_tracker.core.entities.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationDTO {

    private UUID id;
    private String subject;
    private String message;
    private LocalDateTime dateTime;
    private Instant readAt;
    private UserDTO user;

    public NotificationDTO() {
    }

    public NotificationDTO(Notification entity) {
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.message = entity.getMessage();
        this.dateTime = entity.getDateTime();
        this.readAt = entity.getReadAt();
        this.user = new UserDTO(entity.getUser());
    }

    public NotificationDTO(UUID id, String subject, String message, LocalDateTime dateTime, Instant readAt, User user) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.dateTime = dateTime;
        this.readAt = readAt;
        this.user = new UserDTO(user);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Instant getReadAt() {
        return readAt;
    }

    public void setReadAt(Instant readAt) {
        this.readAt = readAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
