package dev.lyze.festive.eventsystem.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnTouchdownEvent extends Event {
    private boolean won;
}
