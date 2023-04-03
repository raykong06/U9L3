import java.util.UUID;

public class CommonLVM {
    private String name;
    private int size;
    private UUID uuid;

    public CommonLVM(String name)
    {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public CommonLVM(String name, int size)
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

    public void setSize(int size){
        this.size = size;
    }

    public UUID getUuid() {
        return uuid;
    }
}
