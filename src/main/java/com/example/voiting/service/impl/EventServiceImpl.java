package com.example.voiting.service.impl;

import com.example.voiting.entity.Event;
import com.example.voiting.entity.Link;
import com.example.voiting.entity.VoitingResult;
import com.example.voiting.service.CodeService;
import com.example.voiting.service.EventService;
import com.example.voiting.service.VoitingService;
import com.example.voiting.system.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private CodeService codeService;

    @Autowired
    private VoitingService voitingService;

    /**
     * TODO: OPTIMIZE
     */
    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        for (int id = 1; id <= Database.getSize(); id++) {
            VoitingResult result = voitingService.getVoitingResultById(id).get();
            Link link = voitingService.getVoitingLink(id).get();
            
            events.add(Event.builder()
                            .id(id)
                            .title("title " + id)
                            .name(result.getVoiting().getName())
                            .description(result.getVoiting().getRank())
                            .date(result.getVoiting().getDate())
                            .link(link.getUrl())
                            .total(result.getVoiceCount())
                            .voteFor(result.getForVoiceCount())
                            .voteAgainst(result.getAgainstVoiceCount())
                            .passwords(codeService.getAllActiveCodes(id))
                    .build());
        }
        return events;
    }
}
