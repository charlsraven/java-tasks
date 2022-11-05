package ru.mail.polis.homework.io.objects;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ����� ����������� ������ ����� ������ � ����������� ������������ 4-�� (2-��) �������� ������.
 * ��� ������������ ���� ������� ������ �� 1000+ ������ �������� (��������� ������� ����� ��������,
 * � ������� ������ Random).
 * ����� ������������ ������ �������� � ���� (���������� ��������� ������ ������, ���� ������ ���������� ����� 5 ������).
 * �� ������ ���� ������ �� ���� � �� �� �������
 *
 * <p>
 * ����� ���� ������ ���� ��������� �� �����.
 *
 * <p>
 * ����������� ����� ������ ���� ���������: ������ �����, ����� ������ � ����� ������.
 * ����� ������� ����� System.currentTimeMillis().
 * � �������� ����������� ������ ���� ���������� �� ���� ��������� ��� ������� �����. (����� 4 �����,
 * �� ������ ���� 1 ����)  � 3 ����� �� ���������� ���������� �����������
 * ��� ������ �������� ����� � ��������������� ������ � ����� ������. ����������� ������ ����� - ��� �������.
 *
 * <p>
 * � ����� ����� �� ������ ������, �� ��������� ������� �����
 */
public class Serializer {

    /**
     * 1 ������
     * ����������� ������� ������������, � ������� ������������ ������ ��� ������������ ��������
     *
     * @param animals  ������ �������� ��� ������������
     * @param fileName ���� � ������� "�����" ��������
     */
    public void defaultSerialize(List<Animal> animals, String fileName) {
        Path fileTo = Paths.get(fileName);
        if (Files.notExists(fileTo)) {
            return;
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(fileTo))) {
            for (Animal animal : animals) {
                outputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 ������
     * ����������� ������� ��������������, � ������� ������������ ������ ��� �������������� ��������
     *
     * @param fileName ���� �� �������� "������" ��������
     * @return ������ ��������
     */
    public List<Animal> defaultDeserialize(String fileName) {

        Path fileFrom = Paths.get(fileName);
        if (Files.notExists(fileFrom)) {
            return Collections.emptyList();
        }

        List<Animal> animals = new ArrayList<>();
        try (InputStream fileInputStream = Files.newInputStream(fileFrom);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (fileInputStream.available() > 0) {
                animals.add((Animal) inputStream.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return animals;
    }


    /**
     * 1 ������
     * ����������� ������� ������ ������������, � ������� ������������ ������ ��� ������������ �������� � ����������� �������
     *
     * @param animals  ������ �������� ��� ������������
     * @param fileName ���� � ������� "�����" ��������
     */
    public void serializeWithMethods(List<AnimalWithMethods> animals, String fileName) {
        Path fileTo = Paths.get(fileName);
        if (Files.notExists(fileTo)) {
            return;
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(fileTo))) {
            for (AnimalWithMethods animal : animals) {
                outputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 ������
     * ����������� ������� ������ ��������������, � ������� ������������ ������ ��� �������������� ��������
     * � ����������� �������
     *
     * @param fileName ���� �� �������� "������" ��������
     * @return ������ ��������
     */
    public List<AnimalWithMethods> deserializeWithMethods(String fileName) {

        Path fileFrom = Paths.get(fileName);
        if (Files.notExists(fileFrom)) {
            return Collections.emptyList();
        }

        List<AnimalWithMethods> animals = new ArrayList<>();
        try (InputStream fileInputStream = Files.newInputStream(fileFrom);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (fileInputStream.available() > 0) {
                animals.add((AnimalWithMethods) inputStream.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    /**
     * 1 ������
     * ����������� ������� ������ ������������, � ������� ������������ ������ ��� ������������ �������� � ���������� Externalizable
     *
     * @param animals  ������ �������� ��� ������������
     * @param fileName ���� � ������� "�����" ��������
     */
    public void serializeWithExternalizable(List<AnimalExternalizable> animals, String fileName) {
        Path fileTo = Paths.get(fileName);
        if (Files.notExists(fileTo)) {
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(fileTo))) {
            for (AnimalExternalizable animal : animals) {
                outputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 ������
     * ����������� ������� ������ ��������������, � ������� ������������ ������ ��� �������������� ��������
     * � ���������� Externalizable
     *
     * @param fileName ���� �� �������� "������" ��������
     * @return ������ ��������
     */
    public List<AnimalExternalizable> deserializeWithExternalizable(String fileName) {
        Path fileFrom = Paths.get(fileName);
        if (Files.notExists(fileFrom)) {
            return Collections.emptyList();
        }
        List<AnimalExternalizable> animals = new ArrayList<>();
        try (InputStream fileInputStream = Files.newInputStream(fileFrom);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            while (fileInputStream.available() > 0) {
                animals.add((AnimalExternalizable) inputStream.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    /**
     * 2 �������
     * ����������� ������ ������������, � ������� ��������������� �������. ���� ������� ����� ����,
     * ��� ������������� ������� writeObject
     *
     * @param animals  ������ �������� ��� ������������
     * @param fileName ����, � ������� "�����" ��������
     */
    public void customSerialize(List<Animal> animals, String fileName) {
        Path fileTo = Paths.get(fileName);
        if (Files.notExists(fileTo)) {
            return;
        }
        try (DataOutputStream outputStream = new DataOutputStream(Files.newOutputStream(fileTo))) {
            for (Animal animal : animals) {
                outputStream.writeUTF(writeString(animal.getName()));
                outputStream.writeUTF(writeString(String.valueOf(animal.getAnimalType())));
                outputStream.writeInt(animal.getCountLegs());
                outputStream.writeByte(animal.isDomesticated() ? 1 : 0);
                outputStream.writeByte(animal.isHerbivore() ? 1 : 0);
                Owner owner = animal.getOwner();
                if (owner != null) {
                    outputStream.writeUTF(writeString(owner.getName()));
                    outputStream.writeBoolean(owner.isOrganization());
                } else {
                    outputStream.writeUTF("null");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 2 �������
     * ����������� ������ ��������������, � ������� ��������������� �������. ���� ������� ������ ����,
     * ��� ������������� ������� readObject
     *
     * @param fileName ���� �� �������� "������" ��������
     * @return ������ ��������
     */
    public List<Animal> customDeserialize(String fileName) {

        Path fileFrom = Paths.get(fileName);
        if (Files.notExists(fileFrom)) {
            return Collections.emptyList();
        }
        List<Animal> animals = new ArrayList<>();
        try (InputStream fileInputStream = Files.newInputStream(fileFrom);
             DataInputStream inputStream = new DataInputStream(fileInputStream)) {
            while (fileInputStream.available() != 0) {
                Animal animal = new Animal();
                animal.setName(readString(inputStream));
                String type = readString(inputStream);
                if (type != null) {
                    animal.setAnimalType(AnimalType.valueOf(type));
                }
                animal.setCountLegs(inputStream.readInt());
                animal.setDomesticated(inputStream.readByte() == 1);
                animal.setHerbivore(inputStream.readByte() == 1);
                String stringOwner = inputStream.readUTF();
                if (!stringOwner.equals("null")) {
                    Owner owner = new Owner();
                    owner.setName(stringOwner);
                    owner.setOrganization(inputStream.readBoolean());
                    animal.setOwner(owner);
                }
                animals.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    private static String writeString(String str) {
        return str == null ? "null" : str;
    }

    private static String readString(DataInputStream inputStream) throws IOException {
        String str = inputStream.readUTF();
        return str.equals("null") ? null : str;
    }
}