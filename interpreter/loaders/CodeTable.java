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
        CodeTable.codeTable.put("GOTO", "GotoByteCode");
        CodeTable.codeTable.put("LIT", "LitByteCode");
        CodeTable.codeTable.put("HALT", "HaltByteCode");
        CodeTable.codeTable.put("POP", "PopByteCode");
        CodeTable.codeTable.put("FALSEBRANCH", "FalseBranchByteCode");
        CodeTable.codeTable.put("STORE", "StoreByteCode");
        CodeTable.codeTable.put("LOAD", "LoadByteCode");
        CodeTable.codeTable.put("ARGS", "ArgsByteCode");
        CodeTable.codeTable.put("CALL", "CallByteCode");
        CodeTable.codeTable.put("RETURN", "ReturnByteCode");
        CodeTable.codeTable.put("BOP", "BopByteCode");
        CodeTable.codeTable.put("READ", "ReadByteCode");
        CodeTable.codeTable.put("WRITE", "WriteByteCode");
        CodeTable.codeTable.put("LABEL", "LabelByteCode");
        CodeTable.codeTable.put("VERBOSE", "VerboseByteCode");

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
