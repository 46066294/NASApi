# NASApi

<html>
<head>
<title>NASApi Mars Rover Photos v1.0</title>
</head>
<body>
<p>NASApi is a project developed with java for the android platform with minSDKVersion 16</p>
<p>  The goal is collect image data gathered by NASA's Curiosity, Opportunity, and Spirit rovers on Mars and make it more easily available to other developers, educators, and citizen scientists.</p>
<p>The data used by the application is obtained from the API</p>
<p>Https://api.nasa.gov/index.html</p>
<p>Https://api.nasa.gov/api.html#MarsPhotos</p>
<p>We can see photos from a total of 606588, spread among the 3 Rover vehicles. The following are the available fields of each Rover:</p>

<table>
		  <thead>
		    <tr>
			  <th>Abbreviation</th>
		      <th>Camera</th>
			  <th class="markerBox">Curiousity</th>
			  <th class="markerBox">Opportunity</th>
			  <th class="markerBox">Spirit</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
				<td>FHAZ</td>
				<td>Front Hazard Avoidance Camera</td>
				<td class="markerBox">✔</td>
				<td class="markerBox">✔</td>
				<td class="markerBox">✔</td>
		    </tr>
		    <tr>
		      <td>RHAZ</td>
		      <td>Rear Hazard Avoidance Camera</td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
		    </tr>
		    <tr>
		      <td>MAST</td>
		      <td>Mast Camera</td>
			<td class="markerBox">✔</td>
			<td class="markerBox"></td>
			<td class="markerBox"></td>
		    </tr>
		    <tr>
		      <td>CHEMCAM</td>
		      <td>Chemistry and Camera Complex</td>
			<td class="markerBox">✔</td>
			<td class="markerBox"></td>
			<td class="markerBox"></td>
		    </tr>
		    <tr>
		      <td>MAHLI</td>
		      <td>Mars Hand Lens Imager</td>
			<td class="markerBox">✔</td>
			<td class="markerBox"></td>
			<td class="markerBox"></td>
		    </tr>
		    <tr>
		      <td>MARDI</td>
		      <td>Mars Descent Imager</td>
			<td class="markerBox">✔</td>
			<td class="markerBox"></td>
			<td class="markerBox"></td>
		    </tr>
		    <tr>
		      <td>NAVCAM</td>
		      <td>Navigation Camera</td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
		    </tr>

		    <tr>
		      <td>PANCAM</td>
		      <td>Panoramic Camera</td>
			<td class="markerBox"></td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
		    </tr>
		    <tr>
		      <td>MINITES</td>
		      <td>Miniature Thermal Emission Spectrometer (Mini-TES)</td>
			<td class="markerBox"></td>
			<td class="markerBox">✔</td>
			<td class="markerBox">✔</td>
		    </tr>


		  </tbody>
		</table>
<p>We can choose the group of photographs classified by Martian day.</p>
<p>All requests are made from the Settings menu.</p>
<p>As an extra, additional information has been added on Rover vehicles and a user's personal notes section in the Navigation Bar menu.</p>
</body>
</html>
