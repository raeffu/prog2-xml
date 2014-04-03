<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h1>Person Details</h1>
				<table border="1">
					<tr>
						<th>Name</th>
						<th>Region</th>
						<th>Salary</th>
						<th>Profession</th>
					</tr>
					<xsl:for-each select="persons">
						<xsl:for-each select="person">
							<tr>
								<td>
									<xsl:value-of select="name" />
								</td>
								<td>
									<xsl:value-of select="region" />
								</td>
								<td>
									<xsl:value-of select="salary" />
								</td>
								<td>
									<xsl:value-of select="profession" />
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet> 