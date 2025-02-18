package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer str = new StringTokenizer(signatureString, List.of("(", " ", ")", ",").toString());
        String accessModifier = str.nextToken();
        String returnType = "";
        if (!accessModifier.equals("private") && !accessModifier.equals("public") && !accessModifier.equals("protected")) {
            returnType = accessModifier;
            accessModifier = null;
        } else {
            returnType = str.nextToken();
        }
        String methodName = str.nextToken();
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        while (str.hasMoreTokens()) {
            arguments.add(new MethodSignature.Argument(str.nextToken(), str.nextToken()));
        }
        MethodSignature signature = new MethodSignature(methodName, arguments);
        signature.setAccessModifier(accessModifier);
        signature.setReturnType(returnType);
        return signature;
    }
}
