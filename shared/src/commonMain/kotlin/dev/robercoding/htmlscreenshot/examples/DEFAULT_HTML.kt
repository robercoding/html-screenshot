package dev.robercoding.htmlscreenshot.examples

internal const val DEFAULT_HTML = """
    <!DOCTYPE html>
                                                                                                    <html>
                                                                                                            <head>
                                                                                                            
                                                                                                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                                                                                        <title>Receipt</title>
                                                                                                        <style>
                                                                                                          html, body { 
    margin: 0; 
    padding: 0; 
    }
    
                                                                                                            table { width: 100%; border-collapse: collapse; }
                                                                                                            th, td { border: 1px solid black; padding: 8px; text-align: left; }
                                                                                                            .total { font-weight: bold; }
                                                                                                        </style>
                                                                                                    </head>
                                                                                                    <body>
                                                                                                        <h1>COnusm</h1>
                                                                                                        <p>Date: 31/03/2025</p>
                                                                                                        <table>
                                                                                                            <thead>
                                                                                                                <tr>
                                                                                                                    <th>Item</th>
                                                                                                                    <th>Quantity/Amount</th>
                                                                                                                    <th>Price</th>
                                                                                                                </tr>
                                                                                                            </thead>
                                                                                                            <tbody>
                                                                                                                
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                      Z                                      <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                        <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>asdasd</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                                                                                                                                <tr>
                                                                                                            <td>HOLA ESTE ES EL ÚLTIMO!!!</td>
                                                                                                            <td>1</td>
                                                                                                            <td>${'$'}1.0</td>
                                                                                                        </tr>
                                                                                                        
                                                                                                            </tbody>
                                                                                                        </table>
                                                                                                        <p class="total">Total: ${'$'}18.0</p>
                                                                                                        <p>Payment Method: N/A</p>
                                                                                                        <p>Contains Warranty: No</p>
                                                                                                    </body>
                                                                                                    </html>
"""

