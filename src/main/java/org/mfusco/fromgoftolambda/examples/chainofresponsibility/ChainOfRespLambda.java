package org.mfusco.fromgoftolambda.examples.chainofresponsibility;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/*
    The chain-of-responsibility pattern is a design pattern consisting of a source of command objects and a series of
    processing objects. Each processing object contains logic that defines the types of command objects that it can
    handle; the rest are passed to the next processing object in the chain. A mechanism also exists for adding new
    processing objects to the end of this chain. Thus, the chain of responsibility is an object oriented version of
    the [if ... else if ... else if ....... else ... endif] idiom, with the benefit that the condition–action blocks
    can be dynamically rearranged and reconfigured at runtime
 */
public class ChainOfRespLambda {

    public static Optional<String> parseText(File file) {
        return Optional.ofNullable(file)
          .filter(f -> f.getType() == File.Type.TEXT )
          .map(f -> "Text file: " + f.getContent());
    }

    public static Optional<String> parsePresentation(File file) {
        return Optional.ofNullable(file)
          .filter(f -> f.getType() == File.Type.PRESENTATION )
          .map(f -> "Presentation file: " + f.getContent());
    }

    public static Optional<String> parseAudio(File file) {
        return Optional.ofNullable(file)
          .filter(f -> f.getType() == File.Type.AUDIO )
          .map(f -> "Audio file: " + f.getContent());
    }

    public static Optional<String> parseVideo(File file) {
        return Optional.ofNullable(file)
          .filter(f -> f.getType() == File.Type.VIDEO )
          .map(f -> "Video file: " + f.getContent());
    }

    public static void main( String[] args ) {
        File file = new File( File.Type.AUDIO, "Dream Theater  - The Astonishing" );

        System.out.println(
        Stream.<Function<File, Optional<String>>>of(
                ChainOfRespLambda::parseText,
                ChainOfRespLambda::parsePresentation,
                ChainOfRespLambda::parseAudio,
                ChainOfRespLambda::parseVideo )
                .map(f -> f.apply( file ))
                .filter( Optional::isPresent )
                .map( Optional::get )
                .findFirst()
                .orElseThrow( () -> new RuntimeException( "Unknown file: " + file ) )
        );
    }
}
