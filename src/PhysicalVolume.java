public class PhysicalVolume extends CommonLVM{
    private PhysicalHardDrive PHD;
    public PhysicalVolume(String name, String PHDname, int size)
    {
        super(name);
        PHD = new PhysicalHardDrive(PHDname, size);
    }
}
