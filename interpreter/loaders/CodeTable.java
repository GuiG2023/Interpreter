/**
 * Code table of byte codes in language X
 *
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 */
package interpreter.loaders;

import java.util.HashMap;
import java.util.Map;

public final class CodeTable {
    private final static Map<String, String> codeTable = new HashMap<>();

    private CodeTable() {
        // do nothing
    }

    /**
     * fill code table with class name mappings
     */
    public static void init() {
        codeTable.put("GOTO", "GotoByteCode");
        codeTable.put("LIT", "LitByteCode");
        codeTable.put("HALT", "HaltByteCode");
        codeTable.put("POP", "PopByteCode");
        codeTable.put("FALSEBRANCH", "FalseBranchByteCode");
        codeTable.put("STORE", "StoreByteCode");
        codeTable.put("LOAD", "LoadByteCode");
        codeTable.put("ARGS", "ArgsByteCode");
        codeTable.put("CALL", "CallByteCode");
        codeTable.put("RETURN", "ReturnByteCode");
        codeTable.put("BOP", "BopByteCode");
        codeTable.put("READ", "ReadByteCode");
        codeTable.put("WRITE", "WriteByteCode");
        codeTable.put("LABEL", "LabelByteCode");
        codeTable.put("VERBOSE", "VerboseByteCode");

    }

    /**
     * Returns the ByteCode class name for a given token.
     *
     * @param token bytecode to map. For example, HALT --> HaltCode
     * @return class name of bytecode
     */
    public static String getClassName(String token) {
        return CodeTable.codeTable.get(token);
    }

}
