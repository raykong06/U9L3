public class PhysicalVolume extends CommonLVM implements Comparable{
    private PhysicalHardDrive drive;
    private VolumeGroup volumeGroup;
    public PhysicalVolume(String name, PhysicalHardDrive drive)
    {
        super(name, drive.getSize());
        this.drive = drive;
    }

    public void setVolumeGroup(VolumeGroup volumeGroup)
    {
        this.volumeGroup = volumeGroup;
    }

    public VolumeGroup getVolumeGroup() {
        return volumeGroup;
    }

    @Override
    public int compareTo(Object o) {
        PhysicalVolume other = (PhysicalVolume) o;
        if (this.getVolumeGroup() != null && other.getVolumeGroup() != null)
        {
            return this.getVolumeGroup().getName().compareTo(other.getVolumeGroup().getName());
        }
        else
        {
            return this.getName().compareTo(other.getName());
        }
    }
}
