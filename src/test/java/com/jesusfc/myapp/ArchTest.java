package com.jesusfc.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.jesusfc.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.jesusfc.myapp.service..")
            .or()
            .resideInAnyPackage("com.jesusfc.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.jesusfc.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
