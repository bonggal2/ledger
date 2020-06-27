package com.prometheus.ledger.service.common.session;

import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.core.util.JSONUtil;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.common.session.model.SessionObject;
import com.prometheus.ledger.service.common.session.result.GetLoginSessionResult;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class SessionServiceImpl implements SessionService{

    @Value("${encryption.key.session}")
    private String sessionEncryptionKey;

    private static final String LOGIN_SESSION_KEY = "LedgerSession";

    @Override
    public boolean saveLoginSession(HttpSession session, String userId) {
        if (StringUtil.isBlank(userId)){
            return false;
        }

        SessionObject sessionObject = new SessionObject();
        sessionObject.setUserId(userId);
        sessionObject.setTimestamp(getCurrentTimestamp());

        createSession(session, sessionObject);
        return true;
    }

    @Override
    public GetLoginSessionResult getLoginSession(HttpSession session) {
        GetLoginSessionResult result = new GetLoginSessionResult();
        if (null == session || null == session.getAttribute(LOGIN_SESSION_KEY)){
            return result;
        }

        if (StringUtil.isBlank(session.getAttribute(LOGIN_SESSION_KEY).toString())){
            return result;
        }

        String sessionValue = EncryptionUtil.decrypt(session.getAttribute(LOGIN_SESSION_KEY).toString(), sessionEncryptionKey);
        JSONObject sessionJson = JSONUtil.convertStringToJSONObject(sessionValue);
        if (null == sessionJson || !sessionJson.containsKey("userId") || !sessionJson.containsKey("timestamp")){
            return result;
        }

        if (StringUtil.isBlank(sessionJson.get("userId").toString()) || null == sessionJson.get("timestamp")){
            return result;
        }

        boolean isAdmin = (boolean)sessionJson.get("isAdmin");
        result.setAdmin(isAdmin);
        result.setUserId(sessionJson.get("userId").toString());
        result.setTimestamp(Long.parseLong(sessionJson.get("timestamp").toString()));
        result.setSuccess(true);

        return result;
    }

    @Override
    public boolean saveAdminLoginSession(HttpSession session, String adminId) {
        if (StringUtil.isBlank(adminId)){
            return false;
        }

        SessionObject sessionObject = new SessionObject();
        sessionObject.setUserId(adminId);
        sessionObject.setAdmin(true);
        sessionObject.setTimestamp(getCurrentTimestamp());
        createSession(session, sessionObject);
        return true;
    }

    private void createSession(HttpSession session, SessionObject sessionObject){
        String sessionValue = EncryptionUtil.encrypt(sessionObject.toJsonString(), sessionEncryptionKey);
        session.setAttribute(LOGIN_SESSION_KEY, sessionValue);
        session.setMaxInactiveInterval(1800); // set inactive time to 30 minutes
    }

    private long getCurrentTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
}
