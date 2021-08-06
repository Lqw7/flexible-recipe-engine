export class Tool {
  /**
   * null or "" both return true
   */
  public static isEmpty (obj: any) {
    if ((typeof obj === 'string')) {
      return !obj || obj.replace(/\s+/g, "") === ""
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  }

  /**
   * Non-empty checks
   */
  public static isNotEmpty (obj: any) {
    return !this.isEmpty(obj);
  }

  /**
   * Object copying
   * @param obj
   */
  public static copy (obj: object) {
    if (Tool.isNotEmpty(obj)) {
      return JSON.parse(JSON.stringify(obj));
    }
  }

  /**
   * Turning arrays into trees using recursion
   * The parent ID attribute is parent
   */
  public static array2Tree (array: any, parentId: number) {

    if (Tool.isEmpty(array)) {
      return [];
    }

    const result = [];
    for (let i = 0; i < array.length; i++) {
      const c = array[i];
    //console.log(Number(c.categoryid), Number(parentId));
      if (c.categoryid == parentId) {
        console.log(c);
        result.push(c);

        // Recursive view of the current node's corresponding children
        const children = Tool.array2Tree(array, c.categoryid);
        if (Tool.isNotEmpty(children)) {
          c.children = children;
        }
      }
    }
    return result;
  }

  /**
   * Randomly generate [radix] decimal numbers of [len] length
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
  public static uuid (len: number, radix = 62) {
    const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    const uuid = [];
    radix = radix || chars.length;

    for (let i = 0; i < len; i++) {
      uuid[i] = chars[0 | Math.random() * radix];
    }

    return uuid.join('');
  }
}
