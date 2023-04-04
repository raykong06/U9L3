public class PhysicalHardDrive extends CommonLVM{
    private PhysicalVolume pv;

    public PhysicalHardDrive(String name, int size)
    {
        super(name, size);
    }

    public void setPv(PhysicalVolume pv)
    {
        this.pv = pv;
    }

    public PhysicalVolume getPv()
    {
        return pv;
    }
}
