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
            else
            {
                System.out.println("Error. Invalid Command.");
            }

            System.out.print("cmd#: ");

            input = s.nextLine();
        }
    }
}
