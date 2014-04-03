<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="girish">
        <html>
            <head>
                <title>Girish</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="roseindia">
        <xsl:value-of select="@key"/>=
        <xsl:value-of select="@value"/>
        <br></br>
    </xsl:template>
</xsl:stylesheet>
