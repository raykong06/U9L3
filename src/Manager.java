import java.util.ArrayList;
import java.util.Collections;

public class Manager {
    private ArrayList<PhysicalHardDrive> physicalHardDrives = new ArrayList<PhysicalHardDrive>();
    private ArrayList<PhysicalVolume> physicalVolumes = new ArrayList<PhysicalVolume>();

    public Manager(){}

    public ArrayList<PhysicalHardDrive> getPhysicalHardDrives() {
        return physicalHardDrives;
    }

    public ArrayList<PhysicalVolume> getPhysicalVolumes() {
        Collections.sort(physicalVolumes);
        return physicalVolumes;
    }

    public void installDrive(String name, int size)
    {
        PhysicalHardDrive drive = new PhysicalHardDrive(name, size);
        physicalHardDrives.add(drive);
    }

    public void installPhysicalVolume(String name, PhysicalHardDrive drive)
    {
        PhysicalVolume physicalVolume = new PhysicalVolume(name, drive);
        physicalVolumes.add(physicalVolume);
        drive.setPv(physicalVolume);
    }
}
