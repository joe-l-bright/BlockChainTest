import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Block;

import static junit.framework.Assert.*;

public class OptionalTests {


    @Test(expected = NoSuchElementException.class)
    public void canThrowNoSuchElementForAppBlockToItem(){
        Optional<Foo> notAFoo = Optional.empty();

        Block<Foo> doThis = (x)->{x.value = "bar";};

        notAFoo.ifPresent(doThis);

        String value = notAFoo.get().value;
    }

    @Test
    public void canAppMultipleBlockToItem(){
        Optional<Foo> aFoo = Optional.of(new Foo("foobar"));

        Block<Foo> doThis = (x)->{x.value = "bar";};
        Block<Foo> doThat = (x)->{x.value = "baz";};
        Block<Foo> doIt = (x)->{x.value = "foo";};


        aFoo.ifPresent(doThis.chain(doThat.chain(doIt)));

        assertEquals("foo", aFoo.get().value);
    }

    public class Foo {
        public Foo(){}
        public Foo(String v){
            value = v;
        }
        public String value;

        public String getValue(){ return value; }
    }
}
