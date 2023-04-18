import java.util.ArrayList;
import java.util.Collections;

public class Manager {
    private ArrayList<PhysicalHardDrive> physicalHardDrives = new ArrayList<PhysicalHardDrive>();
    private ArrayList<PhysicalVolume> physicalVolumes = new ArrayList<PhysicalVolume>();
    private ArrayList<VolumeGroup> volumeGroups = new ArrayList<VolumeGroup>();
    private ArrayList<LogicalVolume> logicalVolumes = new ArrayList<LogicalVolume>();

    public Manager(){}

    public ArrayList<PhysicalHardDrive> getPhysicalHardDrives() {
        return physicalHardDrives;
    }

    public ArrayList<PhysicalVolume> getPhysicalVolumes() {
        Collections.sort(physicalVolumes);
        return physicalVolumes;
    }

    public ArrayList<VolumeGroup> getVolumeGroups() {
        return volumeGroups;
    }

    public ArrayList<LogicalVolume> getLogicalVolumes(){
        return logicalVolumes;
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

    public void installVolumeGroup(String name, PhysicalVolume physicalVolume)
    {
        VolumeGroup volumeGroup = new VolumeGroup(name);
        volumeGroup.addPhysicalVolume(physicalVolume);
        physicalVolume.setVolumeGroup(volumeGroup);
        volumeGroups.add(volumeGroup);
    }

    public void extendVolumeGroup(VolumeGroup volumeGroup, PhysicalVolume physicalVolume)
    {
        int i = volumeGroups.indexOf(volumeGroup);
        volumeGroups.get(i).addPhysicalVolume(physicalVolume);
        physicalVolume.setVolumeGroup(volumeGroup);
    }

    public void installLogicalVolume(String name, int size, VolumeGroup volumeGroup)
    {
        LogicalVolume logicalVolume = new LogicalVolume(name, size, volumeGroup);
        logicalVolumes.add(logicalVolume);

        int i = volumeGroups.indexOf(volumeGroup);
        volumeGroups.get(i).addLogicalVolume(logicalVolume);
    }
}
