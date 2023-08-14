package server;

import com.sun.net.httpserver.HttpHandler;
import handlers.admin.AdminHandler;
import handlers.receipt.GetReceiptTypeHandler;
import handlers.receipt.UpdateReceiptTypeHandler;
import handlers.reimbursement.CreateReimbursementHandler;
import handlers.reimbursement.ReimbursementHandler;
import handlers.settings.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory class responsible for creating a map of context paths to HttpHandlers.
 */
public class HandlerFactory {

    /**
     * Creates and returns a map of context paths to HttpHandlers.
     *
     * @return A map containing context paths as keys and corresponding HttpHandlers as values.
     */
    public static Map<String, HttpHandler> createHandlersMap() {
        Map<String, HttpHandler> handlers = new HashMap<>();
        handlers.put("/reimbursement", new ReimbursementHandler());
        handlers.put("/createReimbursement", new CreateReimbursementHandler());
        handlers.put("/getReceiptTypes", new GetReceiptTypeHandler());
        handlers.put("/getReimbursementRatesSetting", new GetReimbursementRatesSettingHandler());
        handlers.put("/admin", new AdminHandler());
        handlers.put("/updateReimbursementRatesSetting", new UpdateReimbursementRateSettingsHandler());
        handlers.put("/updateReceiptTypes", new UpdateReceiptTypeHandler());
        handlers.put("/getTotalReimbursementCostLimitSetting", new GetTotalReimbursementCostLimitSetting());
        handlers.put("/updateTotalReimbursementCostLimitSetting", new UpdateTotalReimbursementCostLimitSetting());
        handlers.put("/getTotalMileageLimitSetting", new GetTotalMileageLimitSetting());
        handlers.put("/updateTotalMileageLimitSetting", new UpdateTotalMileageLimitSetting());
        return handlers;
    }
}
