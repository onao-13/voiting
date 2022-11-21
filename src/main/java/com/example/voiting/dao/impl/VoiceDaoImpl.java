package com.example.voiting.dao.impl;

import com.example.voiting.dao.VoiceDao;
import com.example.voiting.system.Database;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VoiceDaoImpl implements VoiceDao {

    @Override
    public void saveVoice(Map<String, Object> voice, long id) {
        try {
            DocumentReference ref = Database.VOITING_RESULT_REF.document(String.valueOf(id));
            String voiceKey = "";
            for (Map.Entry<String, Object> entry : voice.entrySet()) {
                voiceKey = entry.getKey();
                break;
            }
            if (isFreeVoices(ref)) {
                long voices = (long) ref.get().get().get(voiceKey);
                voices++;
                Map<String, Object> result = new HashMap<>();
                result.put(voiceKey, voices);
                ref.update(voiceKey, voices);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean isFreeVoices(DocumentReference ref) {
        try {
            DocumentSnapshot snapshot = ref.get().get();
            long allVoices = (long) snapshot.get("voiceCount");
            long forVoices = (long) snapshot.get("forVoiceCount");
            long againstVoices = (long) snapshot.get("againstVoiceCount");
            if (allVoices >= forVoices + againstVoices) return true; else return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
