/*
 * Copyright (C) 2026 IDRsolutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.idrsolutions.examples;

import org.jpedal.tools.pdfa.PdfA3Converter;

import java.io.File;

public class PdfToPdfA {

    public static void main(final String[] args) {

        // The PdfA3Converter class is used to convert an existing PDF file
        // into a PDF/A-3 file suitable for long-term archiving.

        try {

            // Initialize a new instance with the conformance level to convert to
            final PdfA3Converter converter = new PdfA3Converter(PdfA3Converter.Level.B);

            // Attempt to convert the file
            // The output directory will be created if it does not already exist
            // If the file is encrypted, use convert(input, password, output) instead
            final PdfA3Converter.ConversionResult result =
                    converter.convert(new File("inputFile.pdf"), new File("output.pdf"));

            // The conversion was successful if there are no issues
            if (result.issues().isEmpty()) {

                // Get the list of changes made to the file to make it compliant
                System.out.println("Success! Changes:");
                result.changes().forEach(System.out::println);

            } else {

                // Get the list of issues which prevented the file from being converted
                System.out.println("Failure! Issues:");
                result.issues().forEach(System.out::println);
            }

        } catch (Exception e) {
            // An Exception may be thrown if the conversion fails
            e.printStackTrace(System.err);
        }

    }

}
