import java.util.UUID;

public class PhysicalHardDrive {
    private String name;
    private int size;
    private UUID uuid;

    public PhysicalHardDrive(String name, int size)
    {
        this.name = name;
        this.size = size;
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
