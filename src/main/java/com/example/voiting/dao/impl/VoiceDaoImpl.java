package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.entity.Voice;
import com.example.voiting.system.Database;
import com.example.voiting.system.VoiceData;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VoiceDaoImpl implements VoiceDao {

    @Override
    public void saveVoice(String voice, long id) {
        try {
            DocumentReference ref = Database.EVENTS_REF.document(String.valueOf(id));
            long voices = (long) ref.get().get().get(voice);
            voices++;
            ref.update(voice, voices);
        } catch (Exception e) {
            System.out.println("VoiceDao: saveVoice " + e);
        }
    }
}
