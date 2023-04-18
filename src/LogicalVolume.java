public class LogicalVolume extends CommonLVM{
    private VolumeGroup volumeGroup;

    public LogicalVolume(String name, int size, VolumeGroup volumeGroup)
    {
        super(name, size);
        this.volumeGroup = volumeGroup;
    }
}
