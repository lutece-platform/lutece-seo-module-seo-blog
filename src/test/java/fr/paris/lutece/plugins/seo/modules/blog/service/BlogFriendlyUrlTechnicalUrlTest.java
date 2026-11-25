/*
 * Copyright (c) 2002-2020, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.seo.modules.blog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.blog.business.Blog;
import fr.paris.lutece.plugins.blog.business.portlet.BlogPublication;

/**
 * Technical URL and publication chosen by the blog friendly URL generator (no database)
 */
public class BlogFriendlyUrlTechnicalUrlTest
{
    private static final long DAY = 24L * 3600 * 1000;
    private Locale _defaultLocale;

    @BeforeEach
    public void setUp( )
    {
        _defaultLocale = Locale.getDefault( );
    }

    @AfterEach
    public void tearDown( )
    {
        Locale.setDefault( _defaultLocale );
    }

    /**
     * Ids of 1000 and more are written without any grouping separator, whatever the locale
     */
    @Test
    public void testTechnicalUrlOfLargeIds( )
    {
        for ( Locale locale : new Locale [ ] {
                Locale.ENGLISH, Locale.FRANCE, Locale.GERMANY
        } )
        {
            Locale.setDefault( locale );
            assertEquals( "/jsp/site/Portal.jsp?page=blog&id=1000&portlet_id=12345", BlogFriendlyUrlGenerator.buildTechnicalUrl( 1000, 12345 ),
                    locale.toString( ) );
        }
        assertEquals( "/jsp/site/Portal.jsp?page=blog&id=7&portlet_id=3", BlogFriendlyUrlGenerator.buildTechnicalUrl( 7, 3 ) );
    }

    private static BlogPublication publication( int nIdPortlet, Date dateBegin, Date dateEnd )
    {
        BlogPublication publication = new BlogPublication( );
        publication.setIdPortlet( nIdPortlet );
        publication.setDateBeginPublishing( dateBegin == null ? null : new java.sql.Date( dateBegin.getTime( ) ) );
        publication.setDateEndPublishing( dateEnd == null ? null : new java.sql.Date( dateEnd.getTime( ) ) );
        return publication;
    }

    private static Blog blog( boolean bArchived, BlogPublication... publications )
    {
        Blog blog = new Blog( );
        blog.setArchived( bArchived );
        List<BlogPublication> list = new ArrayList<>( );
        for ( BlogPublication publication : publications )
        {
            list.add( publication );
        }
        blog.setBlogPublication( list );
        return blog;
    }
}
