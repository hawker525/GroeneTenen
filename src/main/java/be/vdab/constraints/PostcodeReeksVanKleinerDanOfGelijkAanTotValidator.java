package be.vdab.constraints;

import be.vdab.valueobjects.PostcodeReeks;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maarten Westelinck on 27/02/2017 for groenetenen.
 */
public class PostcodeReeksVanKleinerDanOfGelijkAanTotValidator implements ConstraintValidator<PostcodeReeksVanKleinerDanOfGelijkAanTot, PostcodeReeks> {
    @Override
    public void initialize(PostcodeReeksVanKleinerDanOfGelijkAanTot constraint) {}

   public boolean isValid(PostcodeReeks reeks, ConstraintValidatorContext context) {
        if(reeks.getVanpostcode() == null || reeks.getTotpostcode() == null) return true;
        return reeks.getVanpostcode() <= reeks.getTotpostcode();
   }
}
