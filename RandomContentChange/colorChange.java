View someView = findViewById(android.R.id.content);
View root = someView.getRootView();
Resources res = getResources();
final TypedArray myImages = res.obtainTypedArray(R.array.image);
final Random random = new Random();
int randomInt = random.nextInt(myImages.length());
int drawableID = myImages.getResourceId(randomInt, -1);
root.setBackgroundColor(getResources().getColor(drawableID));