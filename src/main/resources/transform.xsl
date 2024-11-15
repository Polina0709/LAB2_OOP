<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    <xsl:strip-space elements="*"/>

    <!-- Визначення ключа для групування цукерок за типом -->
    <xsl:key name="candiesByType" match="Candy" use="Type"/>

    <xsl:template match="/Candies">
        <root>
            <!-- Групування цукерок за типом -->
            <xsl:for-each select="Candy[generate-id() = generate-id(key('candiesByType', Type)[1])]">
                <xsl:element name="{Type}">
                    <!-- Копіювання всіх елементів цукерок з поточним типом -->
                    <xsl:for-each select="key('candiesByType', Type)">
                        <Candy>
                            <Name><xsl:value-of select="Name"/></Name>
                            <Energy><xsl:value-of select="Energy"/></Energy>
                            <Type><xsl:value-of select="Type"/></Type>
                            <Ingredients>
                                <Water><xsl:value-of select="Ingredients/Water"/></Water>
                                <Sugar><xsl:value-of select="Ingredients/Sugar"/></Sugar>
                                <Fructose><xsl:value-of select="Ingredients/Fructose"/></Fructose>
                                <ChocolateType><xsl:value-of select="Ingredients/ChocolateType"/></ChocolateType>
                                <Vanilla><xsl:value-of select="Ingredients/Vanilla"/></Vanilla>
                            </Ingredients>
                            <Value>
                                <Proteins><xsl:value-of select="Value/Proteins"/></Proteins>
                                <Fats><xsl:value-of select="Value/Fats"/></Fats>
                                <Carbohydrates><xsl:value-of select="Value/Carbohydrates"/></Carbohydrates>
                            </Value>
                            <Production><xsl:value-of select="Production"/></Production>
                        </Candy>
                    </xsl:for-each>
                </xsl:element>
            </xsl:for-each>
        </root>
    </xsl:template>

    <xsl:template match="text()">
        <xsl:value-of select="normalize-space()"/>
    </xsl:template>
</xsl:stylesheet>
