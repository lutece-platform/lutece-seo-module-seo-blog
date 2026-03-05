/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.seo.modules.blog.service;

import fr.paris.lutece.plugins.seo.business.FriendlyUrl;
import fr.paris.lutece.plugins.seo.service.generator.GeneratorOptions;
import fr.paris.lutece.test.LuteceTestCase;

import jakarta.enterprise.inject.spi.CDI;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * BlogFriendlyUrlGeneratorTest
 */
public class BlogFriendlyUrlGeneratorTest extends LuteceTestCase
{

    /**
     * Test of generate method, of class BlogFriendlyUrlGenerator.
     */
    @Test
    public void testGenerate()
    {
        System.out.println("generate");
        List<FriendlyUrl> list = new ArrayList<>();
        GeneratorOptions options = new GeneratorOptions();
        BlogFriendlyUrlGenerator instance = CDI.current( ).select( BlogFriendlyUrlGenerator.class ).get( );
        String expResult = "";
        String result = instance.generate(list, options);
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class BlogFriendlyUrlGenerator.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        BlogFriendlyUrlGenerator instance = CDI.current( ).select( BlogFriendlyUrlGenerator.class ).get( );
        String expResult = "Blog Friendly URL Generator";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

}
