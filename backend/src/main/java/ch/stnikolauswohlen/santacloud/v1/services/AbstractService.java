package ch.stnikolauswohlen.santacloud.v1.services;

import ch.stnikolauswohlen.santacloud.logger.AuditLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService
{
    private final static Logger log = LoggerFactory.getLogger(AbstractService.class);

    private final static AuditLogger auditLogger = new AuditLogger();

    protected void writeLog(final String apiErrorMessage, final Exception exception)
    {
        log.error(apiErrorMessage, exception);
    }

    void writeLog(final String apiErrorMessage)
    {
        log.error(apiErrorMessage);
    }

}
