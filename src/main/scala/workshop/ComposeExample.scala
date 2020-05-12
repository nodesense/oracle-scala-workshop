package workshop

object ComposeExample extends  App {
  // f(x)
  // g(x)

  // f(g(x))
  // g(f(x))

  def f(str: String) = s"f($str)"
  def g(str: String) = s"g($str)"
  def k(str: String) = s"k($str)"
  def l(str: String) = s"l($str)"

  println(f("x"))
  println(g("x"))

  // method _ converts to func

  val f_compose_g = (f _) compose (g _)
  val f_compose_g_compose_k = (f _) compose (g _) compose (k _)
  // invoke g(x) first and then invoke f(g(x))
  // right to left
  println(f_compose_g("x")) // output f(g(x))
  println(f_compose_g_compose_k("x")) // output f(g(k(x)))

  val f_compose_g_compose_k_compose_l = f_compose_g_compose_k compose (l _)
  println(f_compose_g_compose_k_compose_l("x")) // output f(g(k(l(x))))



}
