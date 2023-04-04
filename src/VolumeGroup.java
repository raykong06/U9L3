import java.util.ArrayList;

public class VolumeGroup extends CommonLVM{
    private ArrayList<PhysicalVolume> physicalVolumes;
    private ArrayList<LogicalVolume> logicalVolumes;

    public VolumeGroup(String name, ArrayList<PhysicalVolume> physicalVolumes, ArrayList<LogicalVolume> logicalVolumes)
    {
        super(name);
        this.physicalVolumes = physicalVolumes;
        this.logicalVolumes = logicalVolumes;
    }

    @Override
    public int getSize(){
        int size = 0;
        for (PhysicalVolume pv : physicalVolumes){
            size += pv.getSize();
        }
        super.setSize(size);
        return size;
    }

    public int getSizeLeft(){
        int size = 0;
        for (LogicalVolume lv : logicalVolumes){
            size += lv.getSize();
        }
        size = this.getSize() - size;
        return size;
    }
}
