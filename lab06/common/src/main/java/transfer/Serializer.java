package transfer;

import exceptions.SerializeException;

import java.io.*;

public class Serializer {
    public static byte[] serialize(Serializable request) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(request);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("Ошибка сериализации");
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                return objectInputStream.readObject();
            }
        }
    }
}
