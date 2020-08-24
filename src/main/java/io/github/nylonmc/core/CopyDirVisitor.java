package io.github.nylonmc.core;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyDirVisitor extends SimpleFileVisitor<Path> {

    private Path fromPath;
    private Path toPath;
    private StandardCopyOption copyOption;


    public CopyDirVisitor(Path fromPath, Path toPath, StandardCopyOption copyOption) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.copyOption = copyOption;
    }

    public CopyDirVisitor(Path fromPath, Path toPath) {
        this(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {

        Path targetPath = toPath.resolve(fromPath.relativize(dir).toString());
        if (!Files.exists(targetPath)) {
            Files.createDirectory(targetPath);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {

        Files.copy(file, toPath.resolve(fromPath.relativize(file).toString()), copyOption);
        return FileVisitResult.CONTINUE;
    }
}