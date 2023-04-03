import java.util.ArrayList;
import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";

        Manager LVM = new Manager();
        ArrayList<PhysicalHardDrive> physicalHardDrives = new ArrayList<PhysicalHardDrive>();
        ArrayList<PhysicalVolume> physicalVolumes = new ArrayList<PhysicalVolume>();

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

                PhysicalHardDrive drive = new PhysicalHardDrive(name, size);
                physicalHardDrives.add(drive);
                System.out.println("Drive " + name + " installed");
            }
            else if (input.equals("list-drives"))
            {
                for (PhysicalHardDrive drive : physicalHardDrives)
                {
                    System.out.println(drive.getName() + " [" + drive.getSize() + "G][" + drive.getUuid().toString() + "]");
                }
            }
            else if (input.startsWith("pvcreate"))
            {
                String name = input.substring(input.indexOf(" ") + 1);
                String driveName = name.substring(name.indexOf(" ") + 1);
                name = name.substring(0, name.indexOf(" "));

                for ()
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
