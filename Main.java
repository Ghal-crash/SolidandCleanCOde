// Galih Aditya Fernanda - 21120122120031
// RB Ramzi Akbar A.P.D - 21120122140087
// Nalendra Faraz Putra R - 21120122140098
// Muhammad Erdin Ilham F - 21120122140131
// Raka Eldiansyah Putra - 21120122140150
// Muhammad Dandy Prasetya - 21120122140145

// Single Responsibility Principle (SRP)
// Setiap kelas hanya bertanggung jawab untuk satu hal, entah menghitung bangun datar atau bangun ruang.

interface MenghitungBangunDatar {
    double hitungLuas();
    double hitungKeliling();
}

interface MenghitungBangunRuang {
    double hitungVolume();
    double hitungLuasPermukaan();
}

// Implementasi kelas untuk bangun datar Persegi
class Persegi implements MenghitungBangunDatar {
    private double sisi;

    public Persegi(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public double hitungLuas() {
        return sisi * sisi;
    }

    @Override
    public double hitungKeliling() {
        return 4 * sisi;
    }
}

// Implementasi kelas untuk bangun ruang Kubus
class Kubus implements MenghitungBangunRuang {
    private double sisi;

    public Kubus(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public double hitungVolume() {
        return sisi * sisi * sisi;
    }

    @Override
    public double hitungLuasPermukaan() {
        return 6 * sisi * sisi;
    }
}

// Open/Closed Principle (OCP)
// Kelas baru dapat ditambahkan tanpa mengubah kelas yang ada.

class Lingkaran implements MenghitungBangunDatar {
    private double radius;

    public Lingkaran(double radius) {
        this.radius = radius;
    }

    @Override
    public double hitungLuas() {
        return Math.PI * radius * radius;
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * radius;
    }
}

class Segitiga implements MenghitungBangunDatar {
    private double alas;
    private double tinggi;
    private double sisiA;
    private double sisiB;
    private double sisiC;

    public Segitiga(double alas, double tinggi, double sisiA, double sisiB, double sisiC) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    @Override
    public double hitungLuas() {
        return 0.5 * alas * tinggi;
    }

    @Override
    public double hitungKeliling() {
        return sisiA + sisiB + sisiC;
    }
}

// Liskov Substitution Principle (LSP)
// Kelas PersegiPanjang dapat menggantikan kelas MenghitungBangunDatar
// tanpa mengubah perilaku yang diharapkan.

class PersegiPanjang implements MenghitungBangunDatar {
    private double panjang;
    private double lebar;

    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    @Override
    public double hitungLuas() {
        return panjang * lebar;
    }

    @Override
    public double hitungKeliling() {
        return 2 * (panjang + lebar);
    }
}

// Interface Segregation Principle (ISP)
// Interface dipisah berdasarkan fungsi yang spesifik: MenghitungBangunDatar dan MenghitungBangunRuang.
// Tidak ada kelas yang harus mengimplementasikan metode yang tidak mereka butuhkan.

// Dependency Inversion Principle (DIP)
// KalkulatorBangun bergantung pada abstraksi (interface), bukan implementasi konkrit.
// Kita bisa menggunakan bangun datar atau bangun ruang yang berbeda.

class KalkulatorBangun {
    private MenghitungBangunDatar bangunDatar;
    private MenghitungBangunRuang bangunRuang;

    // Constructor untuk bangun datar
    public KalkulatorBangun(MenghitungBangunDatar bangunDatar) {
        this.bangunDatar = bangunDatar;
    }

    // Constructor untuk bangun ruang
    public KalkulatorBangun(MenghitungBangunRuang bangunRuang) {
        this.bangunRuang = bangunRuang;
    }

    public double hitungLuas() {
        if (bangunDatar != null) {
            return bangunDatar.hitungLuas();
        }
        throw new UnsupportedOperationException("Bangun datar tidak tersedia");
    }

    public double hitungVolume() {
        if (bangunRuang != null) {
            return bangunRuang.hitungVolume();
        }
        throw new UnsupportedOperationException("Bangun ruang tidak tersedia");
    }
}

// Main class untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        // Menghitung luas dan keliling persegi
        Persegi persegi = new Persegi(4);
        KalkulatorBangun kalkulatorPersegi = new KalkulatorBangun(persegi);
        System.out.println("Luas Persegi: " + kalkulatorPersegi.hitungLuas());
        System.out.println("Keliling Persegi: " + persegi.hitungKeliling());

        // Menghitung volume dan luas permukaan kubus
        Kubus kubus = new Kubus(3);
        KalkulatorBangun kalkulatorKubus = new KalkulatorBangun(kubus);
        System.out.println("Volume Kubus: " + kalkulatorKubus.hitungVolume());
        System.out.println("Luas Permukaan Kubus: " + kubus.hitungLuasPermukaan());

        // Menghitung luas dan keliling lingkaran
        Lingkaran lingkaran = new Lingkaran(7);
        KalkulatorBangun kalkulatorLingkaran = new KalkulatorBangun(lingkaran);
        System.out.println("Luas Lingkaran: " + kalkulatorLingkaran.hitungLuas());
        System.out.println("Keliling Lingkaran: " + lingkaran.hitungKeliling());

        // Menghitung luas dan keliling segitiga
        Segitiga segitiga = new Segitiga(3, 4, 3, 4, 5);
        KalkulatorBangun kalkulatorSegitiga = new KalkulatorBangun(segitiga);
        System.out.println("Luas Segitiga: " + kalkulatorSegitiga.hitungLuas());
        System.out.println("Keliling Segitiga: " + segitiga.hitungKeliling());
    }
}
