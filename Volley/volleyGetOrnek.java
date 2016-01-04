 RequestQueue queue = Volley.newRequestQueue(this);
 StringRequest strRequest = new StringRequest(Request.Method.GET, "apiURL",
         new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 try {
                     JSONObject o = new JSONObject(response);
                     JSONArray values = o.getJSONArray("sonuc");
                     for (int i = 0; i < values.length(); i++) {
                         JSONObject sonuc = values.getJSONObject(i);
                     }
                 } catch (JSONException ex) {
                 }
             }
         },
         new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(volleyRequest.this, error.toString(), Toast.LENGTH_SHORT).show();
             }
         });
 strRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
 queue.add(strRequest);