package vaccopsjava;

import org.junit.Before;
import org.junit.Test;

import vaccopsjava.Doctor;
import vaccopsjava.Patient;
import vaccopsjava.VaccOps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Tests {

    private VaccOps vaccOps;
    Doctor d1 = new Doctor("a", 1);
    Doctor d2 = new Doctor("b", 1);
    Doctor d3 = new Doctor("c", 1);
    Patient p1 = new Patient("a", 1, 1, "a");
    Patient p2 = new Patient("b", 1, 2, "b");
    Patient p3 = new Patient("c", 1, 3, "c");

    Doctor d4 = new Doctor("d", 3);
    Doctor d5 = new Doctor("e", 4);
    Doctor d6 = new Doctor("f", 4);
    Doctor d7 = new Doctor("g", 2);
    Doctor d8 = new Doctor("h", 2);
    Patient p4 = new Patient("d", 3, 1, "a");
    Patient p5 = new Patient("e", 3, 1, "a");
    Patient p6 = new Patient("f", 2, 1, "a");
    Patient p7 = new Patient("g", 5, 10, "a");
    Patient p8 = new Patient("h", 5, 5, "a");
    Patient p9 = new Patient("i", 5, 50, "a");
    Patient p10 = new Patient("j", 2, 2, "b");
    Patient p11 = new Patient("k", 1, 2, "a");

    @Before
    public void Setup() {
        this.vaccOps = new VaccOps();
    }

    @Test // 1
    public void TestAddingDoctor() {
        vaccOps.addDoctor(d1);
        var d = new ArrayList<>(this.vaccOps.getDoctors());


        assertEquals(1, d.size());
        assertSame(d.get(0).name, d1.name);
        assertEquals(d.get(0).popularity, d1.popularity);
    }

    @Test // 2
    public void TestAddingMultipleDoctors() {
        for (int i = 0; i < 1000; i++) {
            this.vaccOps.addDoctor(new Doctor(i + "", i));
        }

        assertTrue(this.vaccOps.getDoctors().size() == 1000);
    }

    // 3
    @Test(expected = IllegalArgumentException.class)
    public void TestAddingDoctorAlreadyExistThrowException() {
        this.vaccOps.addDoctor(d1);
        this.vaccOps.addDoctor(d1);
    }

    @Test // 4
    public void TestAddingPatient() {
        this.vaccOps.addDoctor(d1);
        this.vaccOps.addPatient(d1, p1);
        var p = new ArrayList<>(this.vaccOps.getPatients());

        assertTrue(p.size() == 1);
        assertTrue(p.get(0).name == p1.name);
        assertTrue(p.get(0).height == p1.height);
        assertTrue(p.get(0).town == p1.town);
        assertTrue(p.get(0).age == p1.age);
    }

    @Test // 5
    public void TestAddingMultiplePatients() {
        this.vaccOps.addDoctor(d1);
        for (int i = 0; i < 1000; i++) {
            var p = new Patient(i + "", i, i, i + "");
            this.vaccOps.addPatient(d1, p);
        }

        assertTrue(this.vaccOps.getPatients().size() == 1000);
    }

    // 6
    @Test(expected = IllegalArgumentException.class)
    public void TestAddingPatientWithNonExistentDoctorThrowException() {
        this.vaccOps.addPatient(d1, p1);
    }


    @Test // 7
    public void TestNotAddingAnyDoctors() {
        assertTrue(this.vaccOps.getPatients().size() == 0);
    }


    @Test // Performance
    public void TestAddDoctorPerf() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            this.vaccOps.addDoctor(new Doctor(i + "", i));
        }

        long stop = System.currentTimeMillis();
        assertTrue(stop - start <= 20);
    }

    @Test // Performance
    public void TestPatientsFromTown() {
        VaccOps vaccOpsFull = new VaccOps();
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor("d" + i, i);
            vaccOpsFull.addDoctor(doctor);
            for (int j = 0; j < 5; j++) {
                vaccOpsFull.addPatient(doctor, new Patient("p" + (j + i), i, j, "Burgas"));
            }
            vaccOpsFull.addPatient(doctor, new Patient("p" + (50 + i), i, 30, "Varna"));
        }

        assertEquals(15, vaccOpsFull.getPatientsInAgeRange(0, 5).size());
        assertEquals(12, vaccOpsFull.getPatientsInAgeRange(1, 4).size());
        assertEquals(3, vaccOpsFull.getPatientsInAgeRange(6, 30).size());


    }

    @Test // Performance
    public void TestPatientsByAge() {
        VaccOps vaccOpsFull = new VaccOps();
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor("d" + i, i);
            vaccOpsFull.addDoctor(doctor);
            for (int j = 0; j < 5; j++) {
                vaccOpsFull.addPatient(doctor, new Patient("p" + (j + i), i, j, "Burgas"));
            }
            vaccOpsFull.addPatient(doctor, new Patient("p" + (50 + i), i, 30, "Varna"));
        }

        assertEquals(15, vaccOpsFull.getPatientsByTown("Burgas").size());
        assertEquals(3, vaccOpsFull.getPatientsByTown("Varna").size());
        assertEquals(0, vaccOpsFull.getPatientsByTown("Sofia").size());
    }

    @Test // Performance
    public void testGetDoctorsSortedByPatientsCountDescAndNameAsc() {
        VaccOps vaccOpsFull = new VaccOps();
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor("d" + i, i);
            vaccOpsFull.addDoctor(doctor);
            for (int j = 0; j < 5 + i; j++) {
                vaccOpsFull.addPatient(doctor, new Patient("p" + (j + i), i+j, j, "Burgas"));
            }
            vaccOpsFull.addPatient(doctor, new Patient("p" + (50 + i), i, 30, "Varna"));
        }

        List<Doctor> expected = List.of(new Doctor("d2",0), new Doctor("d1",0),new Doctor("d0",0));
        List<Doctor> doctors = vaccOpsFull.getDoctorsSortedByPatientsCountDescAndNameAsc().stream().toList();

        assertEquals(expected.size(), doctors.size());
        for (int i = 0; i < doctors.size(); i++) {
             assertEquals(expected.get(i), doctors.get(i));
        }

        vaccOpsFull.addPatient(new Doctor("d0",0), new Patient("p" + 420, 10, 30, "Varna"));

        expected = List.of(new Doctor("d2",0), new Doctor("d0",0),new Doctor("d1",0));
        doctors = vaccOpsFull.getDoctorsSortedByPatientsCountDescAndNameAsc().stream().toList();

        assertEquals(expected.size(), doctors.size());

        for (int i = 0; i < doctors.size(); i++) {
            assertEquals(expected.get(i), doctors.get(i));
        }
    }

    @Test // Performance
    public void testGetPatientsSortedByDoctorsPopularityAscThenByHeightDescThenByAge() {
        VaccOps vaccOpsFull = new VaccOps();
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor("d" + i, i);
            vaccOpsFull.addDoctor(doctor);
            for (int j = 0; j < 5 + i; j++) {
                vaccOpsFull.addPatient(doctor, new Patient("p" + (j + i), i+j, j, "Burgas"));
            }
            vaccOpsFull.addPatient(doctor, new Patient("p" + (50 + i), i, 30, "Varna"));
        }

        List<Doctor> expected = List.of(new Doctor("d2",0), new Doctor("d1",0),new Doctor("d0",0));
        List<Patient> patients = vaccOpsFull.getPatientsSortedByDoctorsPopularityAscThenByHeightDescThenByAge().stream().toList();
    }

    @Test // Performance
    public void testExistPatient() {
        VaccOps vaccOpsFull = new VaccOps();
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor("d" + i, i);
            vaccOpsFull.addDoctor(doctor);
            for (int j = 0; j < 5 + i; j++) {
                vaccOpsFull.addPatient(doctor, new Patient("p" + (j + i), i+j, j, "Burgas"));
            }
            vaccOpsFull.addPatient(doctor, new Patient("p" + (50 + i), i, 30, "Varna"));
        }

        assertTrue(vaccOpsFull.exist(new Patient("p1",0,0,"")));
        assertFalse(vaccOpsFull.exist(new Patient("p1000",0,0,"")));
    }

}
