package ch.stnikolauswohlen.santacloud.logger;

import ch.stnikolauswohlen.santacloud.entities.AdminOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger
{
    private static final Logger log = LoggerFactory.getLogger(AuditLogger.class);

    public void write(AdminOperation operation, String userId, String message)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(operation);
        sb.append(" - ");
        sb.append("user: ");
        sb.append(userId);
        sb.append(" - ");
        sb.append(message);

        log.info(sb.toString());
    }
}
