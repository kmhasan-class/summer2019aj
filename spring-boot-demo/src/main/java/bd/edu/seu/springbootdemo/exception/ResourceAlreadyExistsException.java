package bd.edu.seu.springbootdemo.exception;

// TODO what if we extended Error instead?
public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String resource) {
        super(resource + " already exists!");
    }
}
