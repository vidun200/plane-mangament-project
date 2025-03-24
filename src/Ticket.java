import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket() {
    }

    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.setPerson(person);
    }


    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void printTicketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("Person Information:");
        getPerson().printInfo(); // Call the printInfo method of the Person object
    }

    public void save(char Rowletter, int seatnumber) {
        String name = String.valueOf(Rowletter) + String.valueOf(seatnumber) + ".txt";
        File file = new File(name);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("seat Row: " + row + "\nseat number:" + seat + "\nprice: €" + price + "\nperson Information:" + "\nName:" + getPerson().getName() + "\nsurname:" + getPerson().getSurname() + "n E-mail:" + getPerson().getEmail());
            bufferedWriter.close();
        }catch(IOException ignored){

        }
    }
    public void delete_file(char rowLetter, int seatNumber) {
        String filePath = String.valueOf(rowLetter)+String.valueOf(seatNumber)+".txt";
        File file = new File(filePath);
        file.delete();
    }
}