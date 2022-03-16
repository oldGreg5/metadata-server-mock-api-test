package extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

@Slf4j
public class AfterTestExecutionExtension implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("---------------------------------------------------------------------------------");
        log.info("Test Passed: " + context.getDisplayName());
        log.info("---------------------------------------------------------------------------------");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("---------------------------------------------------------------------------------");
        log.info("Test Failed: " + context.getDisplayName());
        log.info("Reason: " + cause);
        log.info("---------------------------------------------------------------------------------");
    }

}
