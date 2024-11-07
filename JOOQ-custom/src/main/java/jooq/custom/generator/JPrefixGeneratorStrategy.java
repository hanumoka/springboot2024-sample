package jooq.custom.generator;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class JPrefixGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {

        if(mode == Mode.DEFAULT){
            return "J" + super.getJavaClassName(definition, mode);
        }else if (mode == Mode.POJO){
            return super.getJavaClassName(definition, mode) + "Pojo";
        }

        return super.getJavaClassName(definition, mode);
    }
}
