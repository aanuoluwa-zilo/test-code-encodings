import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProcessor004 {
    private Map<String, Object> config;
    private List<Map<String, Object>> data;
    private int processedCount;
    
    public DataProcessor004(Map<String, Object> config) {
        this.config = config;
        this.data = new ArrayList<>();
        this.processedCount = 0;
    }
    
    public Map<String, Object> processData(List<Map<String, Object>> inputData) {
        Map<String, Object> results = new HashMap<>();
        
        for (Map<String, Object> item : inputData) {
            if (validateItem(item)) {
                Map<String, Object> processed = transformItem(item);
                results.put(item.get("id").toString(), processed);
                processedCount++;
            }
        }
        
        return results;
    }
    
    private boolean validateItem(Map<String, Object> item) {
        String[] requiredFields = {"id", "name", "value"};
        for (String field : requiredFields) {
            if (!item.containsKey(field)) {
                return false;
            }
        }
        return true;
    }
    
    private Map<String, Object> transformItem(Map<String, Object> item) {
        Map<String, Object> processed = new HashMap<>();
        processed.put("id", item.get("id"));
        processed.put("name", item.get("name").toString().toUpperCase());
        processed.put("value", ((Number) item.get("value")).intValue() * 4);
        processed.put("processedAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return processed;
    }
    
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("processedCount", processedCount);
        stats.put("configKeys", config.size());
        stats.put("dataSize", data.size());
        return stats;
    }
}
