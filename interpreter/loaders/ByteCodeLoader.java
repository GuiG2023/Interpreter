package interpreter.loaders;

import interpreter.bytecodes.ByteCode;
import interpreter.loaders.Program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class ByteCodeLoader {
    private String codSourceFileName;

    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     *
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName) {
        this.codSourceFileName = fileName;
    }

    /**
     * Loads a program from a .cod file.
     *
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        Program program = new Program();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] tokens = line.split("\\s+");
                String bytecodename = tokens[0];
                String classname = CodeTable.getClassName(bytecodename);
                Class<?> c = Class.forName("interpreter.bytecodes." + classname);
//                System.out.println(c);
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();
                bc.init(Arrays.asList(tokens));
                program.addCode(bc);
                System.out.println(bc);
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new InvalidProgramException(e, "Failed to read the source code");
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new InvalidProgramException(e, "Failed to load bytecode");
        }
//        try {
//            br = new BufferedReader(new FileReader(this.codSourceFileName));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        if (br != null) {
//            try {
//                br.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return program;
    }
}
