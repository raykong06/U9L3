import java.util.ArrayList;
import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";

        Manager lvm = new Manager();

        System.out.println("Welcome to the LVM system.");
        System.out.print("cmd#: ");

        input = s.nextLine();
        while (!input.equals("exit"))
        {
            if (input.startsWith("install-drive"))
            {
                String name = input.substring(input.indexOf(" ") + 1);
                int size = Integer.parseInt(name.substring(name.indexOf(" ") + 1, name.length() - 1));
                name = name.substring(0, name.indexOf(" "));

                boolean valid = true;
                for (PhysicalHardDrive drive : lvm.getPhysicalHardDrives())
                {
                    if (drive.getName().equals(name))
                    {
                        System.out.println("Error. A drive with this name is already installed.");
                        valid = false;
                    }
                }

                if (valid)
                {
                    lvm.installDrive(name, size);
                    System.out.println("Drive " + name + " installed.");
                }
            }
            else if (input.equals("list-drives"))
            {
                for (PhysicalHardDrive drive : lvm.getPhysicalHardDrives())
                {
                    System.out.println(drive.getName() + " [" + drive.getSize() + "G][" + drive.getUuid().toString() + "]");
                }
            }
            else if (input.startsWith("pvcreate"))
            {
                String name = input.substring(input.indexOf(" ") + 1);
                String driveName = name.substring(name.indexOf(" ") + 1);
                name = name.substring(0, name.indexOf(" "));

                boolean valid = false;
                PhysicalHardDrive intendedDrive = null;

                for (PhysicalHardDrive drive : lvm.getPhysicalHardDrives())
                {
                    if (drive.getName().equals(driveName))
                    {
                        valid = true;
                    }
                }
                if (!valid)
                {
                    System.out.println("Error. No drive can be found under this name.");
                }

                for (PhysicalHardDrive drive : lvm.getPhysicalHardDrives())
                {
                    if (drive.getName().equals(driveName))
                    {
                        if (drive.getPv() != null)
                        {
                            System.out.println("Error. This drive is already assigned to a physical volume.");
                            valid = false;
                        }
                        else
                        {
                            intendedDrive = drive;
                        }
                    }
                }

                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    if (physicalVolume.getName().equals(name))
                    {
                        System.out.println("Error. The name is already assigned to a physical volume.");
                        valid = false;
                    }
                }

                if (valid)
                {
                    lvm.installPhysicalVolume(name, intendedDrive);
                    System.out.println("Physical Volume " + name + " installed.");
                }
            }
            else if (input.equals("pvlist"))
            {
                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    System.out.print(physicalVolume.getName() + ": [" + physicalVolume.getSize() + "G] ");
                    if (physicalVolume.getVolumeGroup() != null)
                    {
                        System.out.print("[" + physicalVolume.getVolumeGroup().getName() + "] ");
                    }
                    System.out.println("[" + physicalVolume.getUuid().toString() + "]");
                }
            }
            else if (input.startsWith("vgcreate"))
            {
                String vgName = input.substring(input.indexOf(" ") + 1);
                String pvName = vgName.substring(vgName.indexOf(" ") + 1);
                vgName = vgName.substring(0, vgName.indexOf(" "));

                boolean valid = false;

                PhysicalVolume intendedPV = null;

                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    if (physicalVolume.getName().equals(pvName))
                    {
                        valid = true;
                    }
                }
                if (!valid)
                {
                    System.out.println("Error. No physical volume can be found under this name.");
                }

                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    if (physicalVolume.getName().equals(pvName))
                    {
                        if (physicalVolume.getVolumeGroup() != null)
                        {
                            System.out.println("Error. This physical volume is already assigned to a volume group.");
                            valid = false;
                        }
                        else
                        {
                            intendedPV = physicalVolume;
                        }
                    }
                }

                for (VolumeGroup volumeGroup : lvm.getVolumeGroups())
                {
                    if (volumeGroup.getName().equals(vgName))
                    {
                        System.out.println("Error. The name is already assigned to a volume group.");
                        valid = false;
                    }
                }

                if (valid)
                {
                    lvm.installVolumeGroup(vgName, intendedPV);
                    System.out.println("Volume Group " + vgName + " installed with Physical Volume " + pvName + ".");
                }
            }
            else if (input.startsWith("vgextend"))
            {
                String vgName = input.substring(input.indexOf(" ") + 1);
                String pvName = vgName.substring(vgName.indexOf(" ") + 1);
                vgName = vgName.substring(0, vgName.indexOf(" "));

                boolean valid = false;
                boolean pvValid = false;
                boolean vgValid = false;

                PhysicalVolume intendedPV = null;
                VolumeGroup intendedVG = null;

                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    if (physicalVolume.getName().equals(pvName))
                    {
                        pvValid = true;
                    }
                }
                if (!pvValid)
                {
                    System.out.println("Error. No physical volume can be found under this name.");
                }

                for (VolumeGroup volumeGroup : lvm.getVolumeGroups())
                {
                    if (volumeGroup.getName().equals(vgName))
                    {
                        vgValid = true;
                        intendedVG = volumeGroup;
                    }
                }
                if (!vgValid)
                {
                    System.out.println("Error. No volume group can be found under this name.");
                }

                if (pvValid && vgValid)
                {
                    valid = true;
                }

                for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                {
                    if (physicalVolume.getName().equals(pvName))
                    {
                        if (physicalVolume.getVolumeGroup() != null)
                        {
                            System.out.println("Error. This physical volume is already assigned to a volume group.");
                            valid = false;
                        }
                        else
                        {
                            intendedPV = physicalVolume;
                        }
                    }
                }

                if (valid)
                {
                    lvm.extendVolumeGroup(intendedVG, intendedPV);
                    System.out.println("Volume Group " + vgName + " extended with Physical Volume " + pvName + ".");
                }
            }
            else if (input.equals("vglist"))
            {
                for (VolumeGroup volumeGroup : lvm.getVolumeGroups())
                {
                    System.out.print(volumeGroup.getName() + ": total: [" + volumeGroup.getSize()
                            + "G] available:[" + volumeGroup.getSizeLeft() + "G] [");
                    boolean first = true;
                    for (PhysicalVolume physicalVolume : lvm.getPhysicalVolumes())
                    {
                        if (physicalVolume.getVolumeGroup() != null && physicalVolume.getVolumeGroup().equals(volumeGroup))
                        {
                            if (!first)
                            {
                                System.out.print(", " + physicalVolume.getName());
                            }
                            else
                            {
                                System.out.print(physicalVolume.getName());
                                first = false;
                            }
                        }
                    }
                    System.out.println("] [" + volumeGroup.getUuid().toString() + "]");
                }
            }
            else if (input.startsWith("lvcreate"))
            {
                String lvName = input.substring(input.indexOf(" ") + 1);
                String strSize = lvName.substring(lvName.indexOf(" ") + 1);
                String vgName = strSize.substring(strSize.indexOf(" ") + 1);
                strSize = strSize.substring(0, strSize.indexOf("G"));
                int size = Integer.parseInt(strSize);
                lvName = lvName.substring(0, lvName.indexOf(" "));

                boolean valid = false;
                boolean lvValid = true;
                boolean vgValid = false;

                VolumeGroup intendedVG = null;

                // no existing lv name
                // find the vg
                // enough space in the vg

                for (VolumeGroup volumeGroup : lvm.getVolumeGroups())
                {
                    if (volumeGroup.getName().equals(vgName))
                    {
                        vgValid = true;
                        intendedVG = volumeGroup;
                    }
                }
                if (!vgValid)
                {
                    System.out.println("Error. No volume group can be found under this name.");
                }

                if (vgValid)
                {
                    if (size > intendedVG.getSizeLeft())
                    {
                        vgValid = false;
                        System.out.println("Error. The intended volume group does not have enough space to fit a logical volume of this size.");
                    }
                }

                for (LogicalVolume logicalVolume : lvm.getLogicalVolumes())
                {
                    if (logicalVolume.getName().equals(lvName))
                    {
                        System.out.println("Error. The name is already assigned to a logical volume.");
                        lvValid = false;
                    }
                }

                if (lvValid && vgValid)
                {
                    valid = true;
                }

                if (valid)
                {
                    lvm.installLogicalVolume(lvName, size, intendedVG);
                    System.out.println("Logical Volume " + lvName + " has been installed to Volume Group " + vgName + ".");
                }
            }
            else
            {
                System.out.println("Error. Invalid Command.");
            }

            System.out.print("cmd#: ");

            input = s.nextLine();
        }
    }
}
