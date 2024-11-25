import java.util.HashMap;

public interface EquipmentSlot {
    Items getSlot(String slotName);
    void setSlot(String slotName, Items item);
    String findEmptySlot();
    Iterable<String> getSlotName();
}

class Slot implements EquipmentSlot {
    private HashMap<String , Items> slots;

    Slot(int i) {
        slots = new HashMap<>(i);
        slots.put("slot1" , null);
        slots.put("slot2" , null);
        slots.put("slot3" , null);
        slots.put("slot4" , null);
    }

    public Items getSlot(String slotName) {
        return slots.getOrDefault(slotName, null);
    }

    public void setSlot(String slotName, Items item) {
        slots.put(slotName, item);
    }

    public String findEmptySlot() {
        for (String slotName : slots.keySet()) {
            if (slots.get(slotName) == null) {
                return slotName;
            }
        }
        return "FULL";
    }

    public Iterable<String> getSlotName() {
        return slots.keySet();
    }
}