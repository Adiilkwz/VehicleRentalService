package com.rental.person1;

import com.rental.person1.observer.Observer;
import com.rental.person1.observer.WebNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ObserverTest {

    private WebNotificationService service;
    private Observer mockObserver1;
    private Observer mockObserver2;

    @BeforeEach
    void setUp() {
        service = new WebNotificationService();
        mockObserver1 = mock(Observer.class);
        mockObserver2 = mock(Observer.class);
    }

    @Test
    void testAttachAndNotify() {
        service.attach(mockObserver1);

        service.notifySubscribers("TEST_EVENT", "Test Message");

        verify(mockObserver1, times(1)).update("TEST_EVENT", "Test Message");
    }

    @Test
    void testDetach() {
        service.attach(mockObserver1);
        service.detach(mockObserver1);

        service.notifySubscribers("TEST_EVENT", "Test Message");

        verify(mockObserver1, never()).update(anyString(), anyString());
    }

    @Test
    void testMultipleObservers() {
        service.attach(mockObserver1);
        service.attach(mockObserver2);

        service.notifySubscribers("EVENT", "MSG");

        verify(mockObserver1).update("EVENT", "MSG");
        verify(mockObserver2).update("EVENT", "MSG");
    }

    @Test
    void testPreventDuplicateAttachment() {
        service.attach(mockObserver1);
        service.attach(mockObserver1);

        service.notifySubscribers("EVENT", "MSG");

        verify(mockObserver1, times(1)).update("EVENT", "MSG");
    }
}